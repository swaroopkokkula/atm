package com.zinkworks.atm.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.zinkworks.atm.exception.InvalidAmountException;
import com.zinkworks.atm.pojo.BillDetail;

@Component
public class NoteDispensor {

	private BillDetail[] billDetails;
	final int smallestBillAmount = 5;
	int availableCash = 0;

	@PostConstruct
	public void init() {
		int[] billTypes = { 50, 20, 10, 5 };
		int[] billCounts = { 10, 30, 30, 20 };

		billDetails = new BillDetail[billTypes.length];

		for (int i = 0; i < billTypes.length; i++) {
			billDetails[i] = new BillDetail(billTypes[i], billCounts[i]);
			availableCash += billTypes[i] * billCounts[i];
		}
	}
	
	public int getAvailableCash() {
		return availableCash;
	}

	private void updateAvailableCash() {
		availableCash = 0;
		for (BillDetail billDetail : billDetails) {
			availableCash += billDetail.getAmount() * billDetail.getCount();
		}
	}

	public List<BillDetail> getBillDetails() {
		List<BillDetail> tmpBillDetails = new ArrayList<>();
		for (BillDetail billDetail : billDetails) {
			tmpBillDetails.add(new BillDetail(billDetail.getAmount(), billDetail.getCount()));
		}

		return tmpBillDetails;
	}

	public List<BillDetail> dispense(int amount) {
		if (amount < smallestBillAmount) {
			throw new InvalidAmountException(
					String.format("Amount in multiples of %s can be dispensed", smallestBillAmount));
		} else if (amount > availableCash) {
			throw new InvalidAmountException("Cannot dispense requested amount");
		}

		List<BillDetail> billDetails = this.getBillDetails(amount);

		this.updateAvailableCash();

		return billDetails;
	}

	private List<BillDetail> getBillDetails(int amount) {
		List<BillDetail> withdrawnBillDetails = new ArrayList<>();
		for (BillDetail billDetail : billDetails) {
			if (amount < 0) {
				break;
			}
			if (billDetail.getCount() == 0) {
				continue;
			}
			if (amount / billDetail.getAmount() > 0) {
				int reqBillCount = amount / billDetail.getAmount();
				int avilableBillCount = Math.min(reqBillCount, billDetail.getCount());

				billDetail.decrementCount(avilableBillCount);

				withdrawnBillDetails.add(new BillDetail(billDetail.getAmount(), avilableBillCount));

				amount = amount - avilableBillCount * billDetail.getAmount();
			}
		}

		return withdrawnBillDetails;
	}
}
