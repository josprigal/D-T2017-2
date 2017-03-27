package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CanBeCommentedRepository;

@Service
@Transactional
public class CanBeCommentedService {

    @Autowired
    private CanBeCommentedRepository canBeCommentedRepository;

    public Double avgComments(){

        return canBeCommentedRepository.avgComments();
    }
}
