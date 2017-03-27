package converters;

import domain.CanBeCommented;
import domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class CanBeCommentedToStringConverter implements Converter<CanBeCommented, String> {

    @Override
    public String convert(final CanBeCommented comment) {

        String result;

        if (comment == null)
            result = null;
        else
            result = String.valueOf(comment.getId());
        return result;

    }

}