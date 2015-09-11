package edu.tamu.ctv.utils.importdata.toxpi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

public class Slice implements Cloneable {
	private int id;
	private String name;
	private String type;
	private String ctype = "Custom type";
	private Color color;
	private Collection<Component> components;
	private int weight;
        private double value;
        private Double[] _95range = new Double[2];
        private ToxPiConfig.SCALING scaling;
        private double missingData;
	
	public Slice() {
	}
	
        public Slice(String name,String weight) {
            this.name = name;
            this.weight = Integer.parseInt(weight);
            
	}
        
        	
	public Slice(int id,
                     String name, 
                     String type, 
                     Collection<Component> components,
		     String weight, 
                     String color,
                     ToxPiConfig.SCALING scaling
                     ) {
                this(name,weight);
		this.id= id;
                this.scaling = scaling;
		
		//for custom type case
		if(!type.toLowerCase().equals(ToxPiConfig.ASSAY) &&
				!type.toLowerCase().equals(ToxPiConfig.PATHWAY) && 
				!type.toLowerCase().equals(ToxPiConfig.CHEMPROP) && 
				!type.toLowerCase().equals(ToxPiConfig.EXPOSURE) && 
				!type.toLowerCase().equals(ToxPiConfig.TOXREFDB)){
					this.type=ToxPiConfig.CUSTOM;
					this.ctype = type;
			}
			else this.type = type;
		
		this.components = components;
        	this.color = Color.decode("0x"+color);
                this.value = 0;
               
	}
        
        public Slice(int id,
                     String name, 
                     String type, 
                     Collection<Component> components,
		     String weight, 
                     Color color,
                     ToxPiConfig.SCALING scaling,
                     double missingData) {
                this(name,weight);
		this.id= id;
                this.scaling = scaling;
                this.missingData = missingData;
		
		//for custom type case
		if(!type.toLowerCase().equals(ToxPiConfig.ASSAY) &&
				!type.toLowerCase().equals(ToxPiConfig.PATHWAY) && 
				!type.toLowerCase().equals(ToxPiConfig.CHEMPROP) && 
				!type.toLowerCase().equals(ToxPiConfig.EXPOSURE) && 
				!type.toLowerCase().equals(ToxPiConfig.TOXREFDB)){
					this.type=ToxPiConfig.CUSTOM;
					this.ctype = type;
			}
			else this.type = type;
		
		this.components = components;
		this.color = color;
                this.value = 0;
	}
        
        public Slice(int id,
                     String name, 
                     String type, 
                     Collection<Component> components,
		     String weight, 
                     Color color,
                     ToxPiConfig.SCALING scaling,
                     double missingData,
                     double value) {
                this(name,weight);
		this.id= id;
                this.scaling = scaling;
                this.missingData = missingData;
		
		//for custom type case
		if(!type.toLowerCase().equals(ToxPiConfig.ASSAY) &&
				!type.toLowerCase().equals(ToxPiConfig.PATHWAY) && 
				!type.toLowerCase().equals(ToxPiConfig.CHEMPROP) && 
				!type.toLowerCase().equals(ToxPiConfig.EXPOSURE) && 
				!type.toLowerCase().equals(ToxPiConfig.TOXREFDB)){
					this.type=ToxPiConfig.CUSTOM;
					this.ctype = type;
			}
			else this.type = type;
		
		this.components = components;
		this.color = color;
                this.value = value;
	}
        
         public Slice(int weight,
                      double value,
                      Color color,
                     String name
                     ) {
		this.id= -1;
		this.name = name;
		//for custom type case
		this.type=ToxPiConfig.ASSAY;
		this.components = new ArrayList<Component>();
		this.weight = weight;
		this.color = color;
	}

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
        
        /**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

        /**
	 * @return the ctype
	 */
	public String getCtype() {
		return ctype;
	}


	/**
	 * @param ctype the ctype to set
	 */
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
        
        public void setColor(String color) {
		this.color = Color.decode(color.replace("#","0x"));
	}
	
	
	
	public String getFinalType(){
		if(this.type.toLowerCase().equals(ToxPiConfig.CUSTOM)) return this.ctype;
		return this.type;
	}
        
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    

    
    public ToxPiConfig.SCALING getScaling(){
        return this.scaling;
    }
    
    public void setScaling(ToxPiConfig.SCALING s){
        this.scaling = s;
    }
    
    public Collection<Component> getComponents() {
        return this.components;
    }
    
    public void setComponents(Collection<Component> components) {
        this.components = components;
    }

    public Double[] get95range() {
        return _95range;
    }

    public void set95range(Double[] _95range) {
        this._95range = _95range;
    }

    public double getMissingData() {
        return missingData;
    }

    public void setMissingData(double missingData) {
        this.missingData = missingData;
    }
    
    
    
    
    @Override
    public Slice clone(){
        final Slice clone = new Slice();
        clone.setColor(this.color);
        clone.setName(this.name);
        clone.setCtype(this.ctype);
        clone.setId(this.id);
        clone.setType(this.type);
        clone.setWeight(this.weight);
        clone.setValue(this.value);
        clone.setScaling(this.scaling);
        clone.setMissingData(this.missingData);
        clone.setComponents(this.components);
        clone.set95range(_95range);
        return clone;
    }
    
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(this.name);
        res.append(", Value: ");
        res.append(Utility.roundThreeDecimals(this.value));
        if(this.scaling!=null){
            res.append(", Scaling: ");
            res.append(this.scaling.toString());
        }
        if(this.missingData>0){
            res.append(", ");
            res.append(this.missingData);
            res.append("% of data is missing");
        }
        return res.toString();
    }

    

	
}

