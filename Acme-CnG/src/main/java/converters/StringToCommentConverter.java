
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentRepository;
import domain.Comment;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment> {

	@Autowired
	CommentRepository	commentRepository;


	@Override
	public Comment convert(final String text) {

		Comment result = null;

		try {

			result = (Comment) Class.forName(text).newInstance();

		} catch (final InstantiationException e) {

			e.printStackTrace();

		} catch (final IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.commentRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
