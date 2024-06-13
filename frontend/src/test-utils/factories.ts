import EnvironmentVariables from "@/config/EnvironmentVariables";
import FetchQuoteFailure from "@/services/QuoteService/types/FetchQuoteFailure";
import FetchQuoteSuccess from "@/services/QuoteService/types/FetchQuoteSuccess";
import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";
import QuoteServiceConfig from "@/services/QuoteService/types/QuoteServiceConfig";

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

export const createQuoteServiceConfig = createFactory<QuoteServiceConfig>({
    host: '127.0.0.1',
    port: 3030
});

export const createEnvironmentVariables = createFactory<EnvironmentVariables>({
    QUOTE_SERVICE_HOST: '127.0.0.1',
    QUOTE_SERVICE_PORT: 3030
});
