import QuoteResponse from "@/services/QuoteService/types/QuoteResponse";

const Quote = ({ response }: { response: QuoteResponse }) => {
    return (
        <p>
            {response.content}
            <br />
            - {response.author}
        </p>
    );
};

export default Quote;
