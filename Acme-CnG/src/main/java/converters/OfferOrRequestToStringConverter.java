
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OfferOrRequest;

@Component
@Transactional
public class OfferOrRequestToStringConverter implements Converter<OfferOrRequest, String> {

	@Override
	public String convert(final OfferOrRequest offerOrRequest) {

		String result;

		if (offerOrRequest == null)
			result = null;
		else
			result = String.valueOf(offerOrRequest.getId());
		return result;

	}
}
