class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer (String name) {
		_name = name;
	}

	public void addRental (Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while(rentals.hasMoreElement()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();

			//determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
				case Movie.REGULAR:
					thisAmount += 2;
					if(each.getDaysRented() > 2)
						thisAmount += (each.getDaysRented() - 2)*1.5;
					break;
				case Movie.NEW_REALEASE:
					thisAmount += each.getDaysRented()*3;
					break;
				case Movie.CHILDREN:
					thisAmount += 1.5;
					if (each.getDaysRented() >3)
						thisAmount += (each.getDaysRented() - 3)*1.5;
					break;
			}

			//add frequent renter points
			frequentRenterPoints++;
			//add bonus for a two day new release rental
			if((each.getMovie().getPriceCode() == Movie.NEW_REALEASE) && each.getDaysRented() > 1)
				frequentRenterPoints++;
			//show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmout;
		}
		//add footer lines
		result += "Amount owed is " + String.valueOf(totalAmout) + "\n";
		result += "You earn " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}