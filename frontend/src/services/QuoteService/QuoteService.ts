import FetchQuoteResult from "./types/FetchQuoteResult";
import QuoteResponse from "./types/QuoteResponse";
import QuoteServiceConfig from "./types/QuoteServiceConfig";

class QuoteService {
    private config: QuoteServiceConfig;

    constructor(config: QuoteServiceConfig) {
        this.config = config;
    }

    async fetchQuote(): Promise<FetchQuoteResult> {
        const { host, port } = this.config;
        const url = `http://${host}:${port}`

        try {
            const response = await fetch(url);

            if (!response.ok)
                throw new Error(`Failed to fetch quote: ${response.status} ${response.statusText}`);

            const data = await response.json();

            return { success: true, response: data as QuoteResponse };
        } catch (error: any) {
            return { success: false, error: error?.message || "Unknown error." };
        }
    }
}

export default QuoteService;
