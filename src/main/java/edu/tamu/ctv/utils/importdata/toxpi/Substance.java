package edu.tamu.ctv.utils.importdata.toxpi;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class Substance implements Comparable<Substance>, Cloneable
{

	private int substance_id = -1;
	private String casrn = null;
	private String name = null;
	private String source_name_sid = null;
	private Double tox_pi_score = 0d;
	private Double[] _95range = new Double[2];
	private List<Slice> slices;
	private int[] rank_range = new int[2];
	private BufferedImage image;
	private BufferedImage imageWithName;
	private Map<String, Double> components;
	private String uniqueName;
	private double angle;
	private String additionalInfo;
	private int rank;

	public Substance(int substance_id, String casrn, String name, String source_name_sid)
	{
		this.substance_id = substance_id;
		this.casrn = casrn;
		this.name = name;
		this.source_name_sid = source_name_sid;
		this.uniqueName = new StringBuffer(String.valueOf(this.substance_id)).append(this.name).append(this.casrn).append(this.source_name_sid).toString();
	}

	public Substance(int substance_id, String casrn, String name, String source_name_sid, Map<String, Double> components)
	{
		this.substance_id = substance_id;
		this.casrn = casrn;
		this.name = name;
		this.source_name_sid = source_name_sid;
		this.components = components;
		this.uniqueName = new StringBuffer(String.valueOf(this.substance_id)).append(this.name).append(this.casrn).append(this.source_name_sid).toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Substance other = (Substance) obj;
		if (this.substance_id != other.substance_id)
		{
			return false;
		}
		if ((this.casrn == null) ? (other.casrn != null) : !this.casrn.equals(other.casrn))
		{
			return false;
		}
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
		{
			return false;
		}
		if ((this.source_name_sid == null) ? (other.source_name_sid != null) : !this.source_name_sid.equals(other.source_name_sid))
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 71 * hash + this.substance_id;
		hash = 71 * hash + (this.casrn != null ? this.casrn.hashCode() : 0);
		hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 71 * hash + (this.source_name_sid != null ? this.source_name_sid.hashCode() : 0);
		return hash;
	}

	/**
	 * @return the substance_id
	 */
	public int getSubstance_id()
	{
		return substance_id;
	}

	/**
	 * @param substance_id
	 *            the substance_id to set
	 */
	public void setSubstance_id(int substance_id)
	{
		this.substance_id = substance_id;
	}

	/**
	 * @return the casrn
	 */
	public String getCasrn()
	{
		return casrn;
	}

	/**
	 * @param casrn
	 *            the casrn to set
	 */
	public void setCasrn(String casrn)
	{
		this.casrn = casrn;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the source_name_sid
	 */
	public String getSource_name_sid()
	{
		return source_name_sid;
	}

	/**
	 * @param source_name_sid
	 *            the source_name_sid to set
	 */
	public void setSource_name_sid(String source_name_sid)
	{
		this.source_name_sid = source_name_sid;
	}

	/**
	 * @return the tox_pi_score
	 */
	public Double getTox_pi_score()
	{
		return tox_pi_score;
	}

	/**
	 * @param tox_pi_score
	 *            the tox_pi_score to set
	 */
	public void setTox_pi_score(Double tox_pi_score)
	{
		this.tox_pi_score = tox_pi_score;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	public String getUniqueName()
	{
		return this.uniqueName;
	}

	public Map<String, Double> getComponents()
	{
		return components;
	}

	public void setComponents(Map<String, Double> components)
	{
		this.components = components;
	}

	public Double[] get95range()
	{
		return _95range;
	}

	public void set95range(Double[] _95range)
	{
		this._95range = _95range;
	}

	public int[] getRank_range()
	{
		return rank_range;
	}

	public void setRank_range(int[] rank_range)
	{
		this.rank_range = rank_range;
	}

	public List<Slice> getSlices()
	{
		return slices;
	}

	public void setSlices(List<Slice> slices)
	{
		this.slices = slices;
	}

	public int compareTo(Substance o)
	{
		if (this.tox_pi_score > o.tox_pi_score) return -1;
		if (this.tox_pi_score.equals(o.tox_pi_score))
		{
			return this.name.compareTo(o.name);
		}
		if (this.tox_pi_score < o.tox_pi_score) return 1;
		return 0;
	}

	@Override
	public String toString()
	{
		return this.name + " [" + this.source_name_sid + ":" + this.casrn + "]";
	}

	@Override
	public Substance clone()
	{
		final Substance res = new Substance(this.substance_id, this.casrn, this.name, this.source_name_sid);
		res.image = this.image;
		res.imageWithName = this.imageWithName;
		res.tox_pi_score = this.tox_pi_score;
		res._95range = this._95range;
		res.rank_range = this.rank_range;
		res.components = this.components;
		res.slices = this.slices;
		res.angle = this.angle;
		res.additionalInfo = this.additionalInfo;
		return res;
	}

	public BufferedImage getImageWithName()
	{
		return imageWithName;
	}

	public void setImageWithName(BufferedImage imageWithName)
	{
		this.imageWithName = imageWithName;
	}

	public String getAdditionalInfo()
	{
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo)
	{
		this.additionalInfo = additionalInfo;
	}

	public double getAngle()
	{
		return angle;
	}

	public void setAngle(double angle)
	{
		this.angle = angle;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}
}