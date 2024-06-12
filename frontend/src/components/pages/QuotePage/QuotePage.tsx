import { useQuoteService } from "@/hooks/useQuoteService";
import FetchQuoteResult from "@/services/QuoteService/types/FetchQuoteResult";
import { useEffect, useState } from "react";
import Quote from "./Quote/Quote";
import LoadingIndicator from "./LoadingIndicator/LoadingIndicator";
import ErrorMessage from "./ErrorMessage/ErrorMessage";

const QuotePage = () => {
    const quoteService = useQuoteService();
    const [fetchResult, setFetchResult] = useState<FetchQuoteResult>();

    useEffect(() => {
        quoteService.fetchQuote().then(result => {
            setFetchResult(result);
            if (!result.success) console.error(result);
        }).catch((error) => {
            setFetchResult({ success: false, error })
            console.error(error);
        });
    }, [quoteService]);

    return (
        <div>
            {!fetchResult && <LoadingIndicator />}
            {fetchResult && fetchResult.success && <Quote response={fetchResult.response} />}
            {fetchResult && !fetchResult.success && <ErrorMessage error={fetchResult.error} />}
        </div>
    );
};

export default QuotePage;
