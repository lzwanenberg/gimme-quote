import multiply from "@/utils/multiply";

const MyComponent = ({ a, b }: { a: number, b: number }) => {
    const result = multiply(a, b);

    return <div>
        {a} multiplied by {b} equals {result}
    </div>;
}

export default MyComponent;
