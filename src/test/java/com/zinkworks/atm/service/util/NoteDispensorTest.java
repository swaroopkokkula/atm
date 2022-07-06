package com.zinkworks.atm.service.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.zinkworks.atm.exception.InvalidAmountException;
import com.zinkworks.atm.pojo.BillDetail;

class NoteDispensorTest {

	@Test
	void shouldUpdateAvailableCashOnInit() {
		NoteDispensor noteDispensor = new NoteDispensor();
		assertEquals(0, noteDispensor.getAvailableCash());

		noteDispensor.init();
		assertEquals(1500, noteDispensor.getAvailableCash());
	}

	@Test
	void shouldUpdateBillDetailsOnInitialization() {
		NoteDispensor noteDispensor = new NoteDispensor();
		noteDispensor.init();
		assertEquals(4, noteDispensor.getBillDetails().size());
	}

	@Test()
	void shouldThrowInvalidAmountExceptionIfAmountIsTooLess() {
		NoteDispensor noteDispensor = new NoteDispensor();
		noteDispensor.init();

		InvalidAmountException exception = assertThrows(InvalidAmountException.class, () -> {
			noteDispensor.dispense(1);
		});

		assertEquals("Amount in multiples of 5 can be dispensed", exception.getMessage());
	}

	@Test()
	void shouldThrowInvalidAmountExceptionIfAmountIsTooHigh() {
		NoteDispensor noteDispensor = new NoteDispensor();
		noteDispensor.init();

		InvalidAmountException exception = assertThrows(InvalidAmountException.class, () -> {
			noteDispensor.dispense(10000);
		});

		assertEquals("Cannot dispense requested amount", exception.getMessage());
	}

	@Test()
	void dispensorShouldReturnCorrectBillDetails() {
		NoteDispensor noteDispensor = new NoteDispensor();
		noteDispensor.init();
		List<BillDetail> billDetails = noteDispensor.dispense(285);

		int amount = 0;
		for (BillDetail d : billDetails) {
			amount = amount + (d.getAmount() * d.getCount());
		}
		assertEquals(4, billDetails.size());
		assertEquals(285, amount);
		assertEquals(1215, noteDispensor.getAvailableCash());
	}

	@Test()
	void dispensorShouldReturnAvailableBillsToFullfillRequestedAmount() {
		NoteDispensor noteDispensor = new NoteDispensor();
		noteDispensor.init();
		noteDispensor.dispense(500);
		List<BillDetail> billDetails = noteDispensor.dispense(50);
		assertEquals(2, billDetails.size());
		int amount = 0;
		for (BillDetail d : billDetails) {
			amount = amount + (d.getAmount() * d.getCount());
		}
		assertEquals(50, amount);
	}
}
