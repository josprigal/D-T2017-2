
package services;

import java.util.Collection;

import domain.OfferOrRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.OfferRepository;
import domain.Offer;
import security.LoginService;

@Service
@Transactional
public class OfferService {

	@Autowired
	OfferRepository	offerRepository;

	@Autowired
	private ActorService actorService;


	public OfferService() {
		super();
	}

	public Offer create() {
		final Offer offer = new Offer();
		return offer;
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

	public Offer save(final Offer offer) {
		Assert.notNull(this.offerRepository);
		final Offer o = this.offerRepository.save(offer);
		return o;
	}

	public void delete(final Offer offer) {
		Assert.notNull(offer);
		Assert.isTrue(offer.getId() != 0);
		Assert.isTrue(this.offerRepository.exists(offer.getId()));
		this.offerRepository.delete(offer);
	}

	public Double avgCommensPerOffer() {
		// TODO Auto-generated method stub
		return this.offerRepository.avgCommensPerOffer();
	}
	public Offer reconstruct(final Offer offer, final BindingResult bindingResult) {

		return offer;
	}

	public Collection<Offer> findAllNotBaned() {
		Collection<Offer> result;
		if (LoginService.hasRole("ADMIN")) {
			result = offerRepository.findAll();
			Assert.notNull(result);
			return result;
		}
		result = offerRepository.findAllNotBaned(actorService.findByPrincipal().getId());
		Assert.notNull(result);

		return result;
	}
}
