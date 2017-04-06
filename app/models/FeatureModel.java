package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class FeatureModel extends Model{
	//----------------------------------------
	// Attributes
	//----------------------------------------
	
	@Id
	public long id;
	
	@Column(nullable = false)
	public String owner;
	
	@Column(nullable = false)
	public String name;
	
	@Column(nullable = false)
	public String extension;
	
	public String file;

}
