import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";

const Quote = ({ response }: { response: QuoteResponse }) => {
    return (
        <p className="animate-fade">
            {response.content}
            <br />
            - <span className="italic">{response.author}</span>
        </p>
    );
};

export default Quote;
