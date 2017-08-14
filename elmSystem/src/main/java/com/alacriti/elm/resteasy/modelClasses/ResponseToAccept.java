package com.alacriti.elm.resteasy.modelClasses;

public class ResponseToAccept {
	int availed_leaves;
	int total_bal_leaves;
	int bal_in_specific;
	
	public ResponseToAccept(int availed_leaves, int total_bal_leaves,
			int bal_in_specific) {
		this.availed_leaves = availed_leaves;
		this.total_bal_leaves = total_bal_leaves;
		this.bal_in_specific = bal_in_specific;
	}
	
	ResponseToAccept(){}
	
	
	public int getAvailed_leaves() {
		return availed_leaves;
	}

	public void setAvailed_leaves(int availed_leaves) {
		this.availed_leaves = availed_leaves;
	}

	public int getTotal_bal_leaves() {
		return total_bal_leaves;
	}

	public void setTotal_bal_leaves(int total_bal_leaves) {
		this.total_bal_leaves = total_bal_leaves;
	}

	public int getBal_in_specific() {
		return bal_in_specific;
	}

	public void setBal_in_specific(int bal_in_specific) {
		this.bal_in_specific = bal_in_specific;
	}

	
}
