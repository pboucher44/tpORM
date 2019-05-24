package fr.epsi.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "channel",
	uniqueConstraints = {@UniqueConstraint(columnNames = "nomChannel")})
public class Channel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String nomChannel;
	
	@ManyToOne
    private Message lesMessages;
    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomChannel() {
		return nomChannel;
	}
	public void setNomChannel(String nomChannel) {
		this.nomChannel = nomChannel;
	}
	public Message getLesMessages() {
		return lesMessages;
	}
	public void setLesMessages(Message lesMessages) {
		this.lesMessages = lesMessages;
	}
	
	

    
}
