import { useQuoteService } from "@/hooks/useQuoteService";
import FetchQuoteResult from "@/services/QuoteService/types/FetchQuoteResult";
import { useEffect, useState } from "react";
import Quote from "./Quote";

const QuotePage = () => {
    const quoteService = useQuoteService();
    const [fetchResult, setFetchResult] = useState<FetchQuoteResult>();

    useEffect(() => {
        quoteService.fetchQuote().then(result => {
            setFetchResult(result);
            if (!result.success) console.error(result);
        });
    }, [quoteService]);

    return (
        <div>
            {fetchResult && fetchResult.success && <Quote response={fetchResult.response} />}
            {fetchResult && !fetchResult.success && "Something went wrong."}
        </div>
    );
};

export default QuotePage;
