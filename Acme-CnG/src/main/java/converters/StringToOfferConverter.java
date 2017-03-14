
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OfferRepository;
import domain.Offer;

@Component
@Transactional
public class StringToOfferConverter implements Converter<String, Offer> {

	@Autowired
	OfferRepository	offerRepository;


	@Override
	public Offer convert(final String text) {

		Offer result = null;

		try {

			result = (Offer) Class.forName(text).newInstance();

		} catch (final InstantiationException e) {

			e.printStackTrace();

		} catch (final IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.offerRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
