/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.irc.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Anna Lukaszek-Zadrozna
 */
public class Channel {
    
    Set<User>  membersList;
    String channelTopic;
    String channelName;
    
    public Channel(String newName){
        channelName = newName;
    }
    
    public Channel(String newName,String newTopic, User member){
       channelName = newName;
        channelTopic = newTopic;
        membersList = new HashSet<>();
        membersList.add(member);
    }
    
    public void changeTopic(String newTopic){
        channelTopic = newTopic;
        
    }
     public boolean isUserInChannel(User user){
        return membersList.contains(user);
    }

    public boolean addUser(User user){
        return membersList.add(user);
    }

    public boolean removeUser(User user){
        return membersList.remove(user);
    }

    public int getSize(){
        return membersList.size();
    }
    
    @Override
    public int hashCode() {
        return channelName.hashCode();
       
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        return channelName.equals(channel.channelName);

       

    }
}
