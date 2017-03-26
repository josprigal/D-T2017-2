
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.PlaceRepository;
import domain.Place;

@Service
@Transactional
public class PlaceService {

	@Autowired
	PlaceRepository	placeRepository;


	public PlaceService() {
		super();
	}

	public Collection<Place> findAll() {
		Collection<Place> result;
		result = this.placeRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Place findOne(final int id) {
		Assert.isTrue(id != 0);
		Place result;
		result = this.placeRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Place place) {
		Assert.notNull(this.placeRepository);
		this.placeRepository.save(place);
	}

	public void delete(final Place place) {
		Assert.notNull(place);
		Assert.isTrue(place.getId() != 0);
		Assert.isTrue(this.placeRepository.exists(place.getId()));
		this.placeRepository.delete(place);
	}

	public Place reconstruct(final Place origin, final BindingResult bindingResult) {
		// TODO Auto-generated method stub
		return origin;
	}

}
