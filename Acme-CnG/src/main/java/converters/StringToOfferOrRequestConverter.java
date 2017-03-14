
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OfferOrRequestRepository;
import domain.OfferOrRequest;

@Component
@Transactional
public class StringToOfferOrRequestConverter implements Converter<String, OfferOrRequest> {

	@Autowired
	OfferOrRequestRepository	offerOrRequestRepository;


	@Override
	public OfferOrRequest convert(final String text) {

		OfferOrRequest result = null;

		try {

			result = (OfferOrRequest) Class.forName(text).newInstance();

		} catch (final InstantiationException e) {

			e.printStackTrace();

		} catch (final IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.offerOrRequestRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
