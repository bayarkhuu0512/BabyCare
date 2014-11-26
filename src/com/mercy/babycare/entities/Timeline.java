package com.mercy.babycare.entities;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class Timeline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5317446034636916461L;

	/**
	 * 
	 */
	@DatabaseField(generatedId = true)
	int id;

	@DatabaseField(canBeNull = false)
	Date createdDate;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Baby baby;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private ActiveOperation activeOperation;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Breast breast;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private ChangeDiaper changeDiaper;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Drink drink;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Feed feed;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Formula formula;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Health health;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private HelpCenter helpCenter;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Learn learn;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private MedCheck medCheck;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private MilkingBreast milkingBreast;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Pain pain;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Purchase purchase;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private TakeMedicine takeMedicine;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Tooth tooth;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Vaccine vaccine;

	@DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
	private Vitamin vitamin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Baby getBaby() {
		return baby;
	}

	public void setBaby(Baby baby) {
		this.baby = baby;
	}

	public ActiveOperation getActiveOperation() {
		return activeOperation;
	}

	public void setActiveOperation(ActiveOperation activeOperation) {
		this.activeOperation = activeOperation;
	}

	public Breast getBreast() {
		return breast;
	}

	public void setBreast(Breast breast) {
		this.breast = breast;
	}

	public ChangeDiaper getChangeDiaper() {
		return changeDiaper;
	}

	public void setChangeDiaper(ChangeDiaper changeDiaper) {
		this.changeDiaper = changeDiaper;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public HelpCenter getHelpCenter() {
		return helpCenter;
	}

	public void setHelpCenter(HelpCenter helpCenter) {
		this.helpCenter = helpCenter;
	}

	public Learn getLearn() {
		return learn;
	}

	public void setLearn(Learn learn) {
		this.learn = learn;
	}

	public MedCheck getMedCheck() {
		return medCheck;
	}

	public void setMedCheck(MedCheck medCheck) {
		this.medCheck = medCheck;
	}

	public MilkingBreast getMilkingBreast() {
		return milkingBreast;
	}

	public void setMilkingBreast(MilkingBreast milkingBreast) {
		this.milkingBreast = milkingBreast;
	}

	public Pain getPain() {
		return pain;
	}

	public void setPain(Pain pain) {
		this.pain = pain;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public TakeMedicine getTakeMedicine() {
		return takeMedicine;
	}

	public void setTakeMedicine(TakeMedicine takeMedicine) {
		this.takeMedicine = takeMedicine;
	}

	public Tooth getTooth() {
		return tooth;
	}

	public void setTooth(Tooth tooth) {
		this.tooth = tooth;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Vitamin getVitamin() {
		return vitamin;
	}

	public void setVitamin(Vitamin vitamin) {
		this.vitamin = vitamin;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
