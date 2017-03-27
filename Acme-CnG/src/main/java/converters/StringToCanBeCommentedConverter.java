package converters;

import domain.Banner;
import domain.CanBeCommented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CanBeCommentedRepository;


@Component
@Transactional
    public class StringToCanBeCommentedConverter implements Converter<String, CanBeCommented> {

    @Autowired
    private CanBeCommentedRepository canBeCommentedRepository;


    public CanBeCommented convert(final String text) {

        CanBeCommented result;

        result = canBeCommentedRepository.findOne(Integer.valueOf(text));
        Assert.notNull(result);

        return result;

    }

}