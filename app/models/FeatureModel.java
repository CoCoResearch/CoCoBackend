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
	public String ownerEmail;
	
	@Column(nullable = false)
	public String name;
	
	@Column(nullable = true)
	public String description;
	
	@Column(nullable = false)
	public String extension;
	
	@Column(nullable = true)
	public String file;
	
	public static Finder<Long, FeatureModel> find = new Finder<Long,FeatureModel>(FeatureModel.class);
}
