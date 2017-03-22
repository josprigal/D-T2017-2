
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import domain.Offer;

@Service
@Transactional
public class OfferService {

	@Autowired
	OfferRepository	offerRepository;


	public OfferService() {
		super();
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;
		result = this.offerRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Offer findOne(final int id) {
		Assert.isTrue(id != 0);
		Offer result;
		result = this.offerRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Offer offer) {
		Assert.notNull(this.offerRepository);
		this.offerRepository.save(offer);
	}

	public void delete(final Offer offer) {
		Assert.notNull(offer);
		Assert.isTrue(offer.getId() != 0);
		Assert.isTrue(this.offerRepository.exists(offer.getId()));
		this.offerRepository.delete(offer);
	}

	public Double avgApplicationsPerOffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double avgCommensPerOffer() {
		// TODO Auto-generated method stub
		return null;
	}

}
