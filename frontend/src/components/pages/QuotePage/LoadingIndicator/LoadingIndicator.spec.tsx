import React from 'react';
import { render } from '@testing-library/react';
import LoadingIndicator from './LoadingIndicator';

describe('LoadingIndicator', () => {
    test('renders role=status element', () => {
        const { getByRole } = render(<LoadingIndicator />);

        const loadingIndicatorContainer = getByRole('status');

        expect(loadingIndicatorContainer).toBeInTheDocument();
    });

    test("renders svg", () => {
        const { getByRole } = render(<LoadingIndicator />);

        const svg = getByRole('status')
            .querySelector('svg');

        expect(svg).toBeInTheDocument();
    });

    test('renders static text for screen readers', () => {
        const { getByText } = render(<LoadingIndicator />);

        const loadingText = getByText(/Loading.../i);

        expect(loadingText).toBeInTheDocument();
        expect(loadingText).toHaveClass('sr-only');
    });
});

