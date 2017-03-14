
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PlaceRepository;
import domain.Place;

@Component
@Transactional
public class StringToPlaceConverter implements Converter<String, Place> {

	@Autowired
	PlaceRepository	placeRepository;


	@Override
	public Place convert(final String text) {

		Place result = null;

		try {

			result = (Place) Class.forName(text).newInstance();

		} catch (final InstantiationException e) {

			e.printStackTrace();

		} catch (final IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.placeRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
