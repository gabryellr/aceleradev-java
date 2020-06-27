package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository repository;

    @Override
    public Quote getQuote() {
        Random random = new Random();
        long count = repository.count();

        int intRandom = random.nextInt((int) count);

        return repository.findById(intRandom).orElseThrow(RuntimeException::new);
    }

    @Override
    public Quote getQuoteByActor(String actor) {
        List<Quote> quoteList = repository.findByActor(actor);

        Random random = new Random();
        return quoteList.get(random.nextInt(quoteList.size()));
    }

}
