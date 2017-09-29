package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories
@RestController
public class RestControllger {

	@Autowired
	public UserDao userDao;
	@Autowired
	public MessageDao messageDao;
	@Autowired
	public ChatroomDao ChatroomDao;
	@Autowired
	public ChatDao ChatDao;
	@Autowired
	public MessageDao MessageDao;

	@RequestMapping("users")
	public List<user> userall() {
		List<user> userListe = new ArrayList<user>();
		userListe = (List<user>) userDao.findAll();
		Iterable<user> contacts = new ArrayList<user>();
		System.out.println(contacts);
		System.out.println("..........");
		System.out.println(userListe.size());
		return userListe;
	}

	@RequestMapping("chats")
	public List<chat> chatsall() {
		List<chat> allchats = new ArrayList<chat>();
		allchats = (List<chat>) ChatDao.findAll();
		System.out.println("");
		System.out.println("..........");
		System.out.println(allchats.size());
		return allchats;
	}

	@RequestMapping("chatrooms")
	public List<chatroom> chatroomsall() {
		List<chatroom> allchats = new ArrayList<chatroom>();
		allchats = (List<chatroom>) ChatroomDao.findAll();
		System.out.println("");
		System.out.println("..........");
		System.out.println(allchats.size());
		return allchats;
	}

	@RequestMapping("messages")
	public List<message> messagesall() {
		List<message> allchats = new ArrayList<message>();
		allchats = (List<message>) MessageDao.findAll();
		for (message a : allchats) {
			System.out.println(a.getDate());
			System.out.println(a.getId());

		}
		System.out.println("");
		System.out.println("..........");
		System.out.println(allchats.size());
		return allchats;
	}

	@RequestMapping("contactlist")
	public Set<usermin> contactlist() {
		user logged = getcurrentuser();
		Set<user> liste = new HashSet<user>();
		Set<Integer> integer = new HashSet<Integer>();
		Set<usermin> usermins = new HashSet<usermin>();

		integer.addAll(logged.getReceiver());
		integer.addAll(logged.getSender());

		for (Integer a : integer) {
			user neu = userDao.findOne(a);
			liste.add(neu);
		}
		for (user a : liste) {
			usermin bud = new usermin(a);
			usermins.add(bud);
		}

		return usermins;
	}

	@RequestMapping(value = "/addmessage", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody messagein messagein) {
		message messagenew = new message();
		List<message> test = (List<message>) MessageDao.findAll();
		messagenew.setId(test.size() + 1);
		messagenew.setMessage(messagein.getMessage());
		System.out.println(messagein.getMessage());
		MessageDao.save(messagenew);

	}

	@RequestMapping(value = "/getchat", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<chatbase> update(@RequestBody usermin idchatrequest) {
		user logged = getcurrentuser();
		user request = userDao.findOne(idchatrequest.id);
		Set<chatbase> returnchat = new HashSet<chatbase>();
		Set<chatbase> returnchat2 = new HashSet<chatbase>();
		List<chat> chats = (List<chat>) ChatDao.findAll();
		List<chatroom> chatrooms = (List<chatroom>) ChatroomDao.findAll();

		for (chatroom a : chatrooms) {
			if (a.getReceiverid() == logged.getId() && a.getSenderid() == request.getId()) {
				chatbase dieserchatbase = new chatbase(messageDao.findOne(a.getMessageid()).getMessage(), true,
						messageDao.findOne(a.getMessageid()).getDate());
				returnchat.add(dieserchatbase);
			}
			if (a.getReceiverid() == request.getId() && a.getSenderid() == logged.getId()) {
				chatbase dieserchatbase = new chatbase(messageDao.findOne(a.getMessageid()).getMessage(), false,
						messageDao.findOne(a.getMessageid()).getDate());
				returnchat.add(dieserchatbase);
			}
		}

		return returnchat;

	}

	@RequestMapping("loggedUser")
	public user getuser() {

		return getcurrentuser();
	}

	// get logged User
	public user getcurrentuser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		user logged = userDao.findOne(Integer.parseInt(name));
		return logged;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/newmessage", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void Submit(@RequestBody messagenew input) {
		user luser = getcurrentuser();
		System.out.println(input.usermin);
		System.out.println(input.message);
		@SuppressWarnings("unused")
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with java.util.Date
		Date date = new Date();

		message newmessage = new message();
		newmessage.setMessage(input.message);
		newmessage.setDate(date);
		MessageDao.save(newmessage);
		List<message> messageList = (List<message>) MessageDao.findAll();
		int messageid = 0;
		for (message s : messageList) {
			if (s.getMessage().equals(input.message)) {
				messageid = s.getId();
			}
		}

		chatroom chatroomnew = new chatroom();
		chatroomnew.setSenderid(input.usermin);
		chatroomnew.setReceiverid(luser.getId());
		chatroomnew.setMessageid(messageid);
		ChatroomDao.save(chatroomnew);

	}
}
