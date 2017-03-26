
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfferOrRequestRepository;
import domain.Offer;
import domain.OfferOrRequest;
import domain.Request;

@Service
@Transactional
public class OfferOrRequestService {

	@Autowired
	OfferService				offerService;
	@Autowired
	RequestService				requestService;
	@Autowired
	OfferOrRequestRepository	offerOrRequestRepository;


	public OfferOrRequestService() {
		super();
	}

	public Collection<OfferOrRequest> findAll() {
		Collection<OfferOrRequest> result;
		result = this.offerOrRequestRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public OfferOrRequest findOne(final int id) {
		Assert.isTrue(id != 0);
		OfferOrRequest result;
		result = this.offerOrRequestRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final OfferOrRequest offerOrRequest) {
		Assert.notNull(this.offerOrRequestRepository);
		this.offerOrRequestRepository.save(offerOrRequest);
	}

	public void delete(final OfferOrRequest offerOrRequest) {
		Assert.notNull(offerOrRequest);
		Assert.isTrue(offerOrRequest.getId() != 0);
		Assert.isTrue(this.offerOrRequestRepository.exists(offerOrRequest.getId()));
		this.offerOrRequestRepository.delete(offerOrRequest);
	}

	public Double ratioOffersVersusRequest() {
		// TODO Auto-generated method stub

		return (double) (this.offerService.findAll().size() / this.requestService.findAll().size());

	}

	public Collection<Offer> findBySearchOffer(final String search) {
		final Collection<Offer> offerOrRequests = this.offerService.findAll();
		final List<Offer> offerOrRequestsFound = new ArrayList<Offer>();
		for (final Offer offerOrRequest : offerOrRequests)
			if (offerOrRequest.getTitle().contains(search) || offerOrRequest.getDescription().contains(search) || offerOrRequest.getPlace().getAddress().contains(search))
				offerOrRequestsFound.add(offerOrRequest);
		return offerOrRequestsFound;
	}

	public Collection<Request> findBySearchRequest(final String search) {
		final Collection<Request> offerOrRequests = this.requestService.findAll();
		final List<Request> offerOrRequestsFound = new ArrayList<Request>();
		for (final Request offerOrRequest : offerOrRequests)
			if (offerOrRequest.getTitle().contains(search) || offerOrRequest.getDescription().contains(search) || offerOrRequest.getPlace().getAddress().contains(search))
				offerOrRequestsFound.add(offerOrRequest);
		return offerOrRequestsFound;
	}
	public Double avgApplicationsPerOfferOrRequest() {
		return this.offerOrRequestRepository.avgApplicationsPerOfferOrRequest();
	}

}
