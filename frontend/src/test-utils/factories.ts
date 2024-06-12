import FetchQuoteFailure from "@/services/QuoteService/types/FetchQuoteFail";
import FetchQuoteSuccess from "@/services/QuoteService/types/FetchQuoteSuccess";
import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";

const createFactory = <T,>(defaults: T) => (overrides: Partial<T> = {}) => ({
    ...defaults,
    ...overrides
});

export const createQuoteResponse = createFactory<QuoteResponse>({
    content: 'Do. Or do not. There is no try.',
    author: 'Yoda',
});

export const createFetchQuoteFailure = createFactory<FetchQuoteFailure>({
    success: false,
    error: "Something went wrong."
});

export const createFetchQuoteSuccess = createFactory<FetchQuoteSuccess>({
    success: true,
    response: createQuoteResponse()
});
