import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";

const Quote = ({ response }: { response: QuoteResponse }) => (
    <p className="animate-fade">
        <span>{response.content}</span>
        <br />
        - <span className="italic">{response.author}</span>
    </p>
);

export default Quote;
