import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";

export const createQuoteResponse = (overrides: Partial<QuoteResponse> = {}): QuoteResponse => ({
    content: 'Do. Or do not. There is no try.',
    author: 'Yoda',
    ...overrides
});
