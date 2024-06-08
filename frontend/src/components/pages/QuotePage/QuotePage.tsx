import { useQuoteService } from "@/hooks/useQuoteService";
import FetchQuoteResult from "@/services/QuoteService/types/FetchQuoteResult";
import { useEffect, useState } from "react";

const QuotePage = () => {
    const quoteService = useQuoteService();
    const [fetchResult, setFetchResult] = useState<FetchQuoteResult>();

    useEffect(() => {
        quoteService.fetchQuote().then(setFetchResult);
    }, [quoteService]);

    return (
        <div>
            <div>TODO:</div>
            {
                fetchResult ? <div>{JSON.stringify(fetchResult)}</div> : "loading..."
            }
        </div>
    );
};

export default QuotePage;
