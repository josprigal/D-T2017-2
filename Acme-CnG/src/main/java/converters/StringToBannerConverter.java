
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.BannerRepository;
import domain.Banner;

@Component
@Transactional
public class StringToBannerConverter implements Converter<String, Banner> {

	@Autowired
	BannerRepository	bannerRepository;


	@Override
	public Banner convert(final String text) {

		Banner result = null;

		try {

			result = (Banner) Class.forName(text).newInstance();

		} catch (final InstantiationException e) {

			e.printStackTrace();

		} catch (final IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.bannerRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
