import { render } from '@testing-library/react'
import '@testing-library/jest-dom'
import MyComponent from './MyComponent';

describe("MyComponent", () => {
    test("multiplies provided values", () => {
        const a = 3;
        const b = 4;

        const result = render(<MyComponent a={a} b={b} />);

        expect(result.getByText('3 multiplied by 4 equals 12'))
            .toBeInTheDocument();
    });
});
