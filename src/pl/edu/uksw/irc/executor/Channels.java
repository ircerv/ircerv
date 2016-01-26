/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.blic 
 */
package pl.edu.uksw.irc.executor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Anna Łukaszek-Zadrożna
 */
public class Channels {
    Set<Channel> channels;
    
    

    public Channels() {
        channels = new HashSet<>();
    }
    /**
     * 
     * @param member
     * @param channelsToJoin 
     */
    public void join(User member, String[] channelsToJoin){
        if(channelsToJoin.length == 1 && channelsToJoin[0].equals("0")){
            for(Channel ch: channels){
                if(ch.isUserInChannel(member)){
                    ch.removeUser(member);
                }
            }
            
        }else{
            for (String channelsToJoin1 : channelsToJoin) {
                if (!channels.contains(new Channel(channelsToJoin1))) {
                    channels.add(new Channel(channelsToJoin1, "", member));
                } else {
                    for (Channel channel : channels) {
                        if (channel.channelName.equals(channelsToJoin1)) {
                            channel.addUser(member);
                        }
                    }
                }
            }
        }   
        
        
        
        
       /* if(channelsToJoin == null){    
            Iterator<User> iterator ;
            for(Channel ch:channels){
                for(iterator= ch.membersList.iterator(); iterator.hasNext();){
                    User u = iterator.next();       
                    if(u.userKey == member.userKey){
                        iterator.remove();
                        
                    }
                }
            }
            
        }else{
            for(Channel ch: channelsToJoin){
                Iterator<Channel> iterator;
                boolean exists = false;
                for(iterator = channels.iterator();iterator.hasNext();){
                    Channel chIterator = iterator.next();
                    if(chIterator.channelName.equals(ch.channelName))
                        exists = true;
                
                }
                if(!exists)
                    channels.add(new Channel(ch.channelName,ch.channelTopic,member));
                else
                    for(Channel chFind: channels){
                        if(chFind.channelName.equals(ch.channelName))
                            chFind.membersList.add(member);
                    }
            }
        }*/
        
    }
    
    public boolean isChannelInList(Channel channel){
        return channels.contains(channel);
    }
    public void confirm(){
        
    }
    
    public void part(User member, String[] channelsToLeave){
        Iterator<Channel> iterator;
              
        for (iterator = channels.iterator(); iterator.hasNext();) {
            Channel chIterator = iterator.next();
            for (String channel : channelsToLeave) {
                if (chIterator.channelName.equals(channel)) {
                    chIterator.removeUser(member);

                }
            }
        }

    }
    
}
