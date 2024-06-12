import FetchQuoteResult from "./FetchQuoteResult";

type QuoteService = {
    fetchQuote: () => Promise<FetchQuoteResult>;
};

export default QuoteService;


