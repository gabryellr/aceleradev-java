package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/quote")
@RestController
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@GetMapping
	public Quote getQuote() {
		return quoteService.getQuote();
	}

	@GetMapping("{actor}")
	public Quote getQuoteByActor(@PathVariable("actor") String actor) {
		return quoteService.getQuoteByActor(actor);
	}

}
