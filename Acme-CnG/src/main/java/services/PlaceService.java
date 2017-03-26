
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PlaceRepository;
import domain.Place;

@Service
@Transactional
public class PlaceService {

	@Autowired
	PlaceRepository		placeRepository;
	@Autowired
	private Validator	validator;


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

	public Place reconstruct(final Place origin, final BindingResult bindingResult, final boolean edit) {
		Place result = null;

		if (!edit)
			return origin;
		else {
			final Collection<Place> places = this.findAll();
			for (final Place p : places)
				if (origin.getAddress().equals(p.getAddress()) && origin.getGpsCoordinates().equals(p.getGpsCoordinates()))
					result = p;

		}

		this.validator.validate(result, bindingResult);

		return result;
	}
}
