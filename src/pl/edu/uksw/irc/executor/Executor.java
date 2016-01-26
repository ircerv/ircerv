package pl.edu.uksw.irc.executor;

import pl.edu.uksw.irc.dto.MessageDTO;
import pl.edu.uksw.irc.queue.EventBus;

/**
 * Created by sok_pomaranczowy on 23.01.16.
 */
public class Executor implements Runnable {

    private EventBus eventBus;
    private UserRepository userRepo;
    private MessageParser messageParser;

    public Executor(EventBus eventBus) {
        this.eventBus = eventBus;
        
    }

    @Override
    public void run() {

        while (true) {

            MessageDTO incomingEvent = eventBus.getIncomingEvent();
            MessageDTO outgoingEvent = null;

            switch (incomingEvent.getCommand()) {
                case JOIN:
                    outgoingEvent = join(incomingEvent);
                case NICK:
                    outgoingEvent = nick(incomingEvent);
                case PING:
                    outgoingEvent = ping(incomingEvent);
                case PRIVMSG:
                    outgoingEvent = privmsg(incomingEvent);
                case QUIT:
                    outgoingEvent = quit(incomingEvent);
                case USER:
                    outgoingEvent = user(incomingEvent);
                default:
                    break;
            }

            if (outgoingEvent != null) {
                eventBus.pushOutgoingEvent(outgoingEvent);
            }
        }
    }

    private MessageDTO quit(MessageDTO incomingEvent) {
        messageParser.parseMessage(incomingEvent);
        userRepo.removeUser(incomingEvent.getFrom(),incomingEvent.getUser(),incomingEvent.getHost());
        return null; // I don't know what kind of rendered message to send (AL-Z)
        
    }

    private MessageDTO privmsg(MessageDTO incomingEvent) {
        return null;
    }

    private MessageDTO ping(MessageDTO incomingEvent) {
        boolean pong;
        messageParser.parseMessage(incomingEvent);
        User userTo = new User(incomingEvent.getTo(),incomingEvent.getUser(),incomingEvent.getHost());
        pong = userRepo.pong(incomingEvent.getFrom(), userTo);
        return null; // if pong- render outmessage replacing to and from user with the same message content 
   }

    private MessageDTO nick(MessageDTO incomingEvent) {
        return null;
    }

    private MessageDTO join(MessageDTO incomingEvent) {
        return null;
    }
    private MessageDTO user(MessageDTO incomingEvent) {
        return null;
    }
}