import React from 'react';
import { render, screen } from '@testing-library/react';
import ErrorMessage from './ErrorMessage';

describe('ErrorMessage', () => {
    test('renders error message', () => {
        render(<ErrorMessage />);

        const contentText = screen.getByText('Something went wrong.');
        expect(contentText).toBeInTheDocument();
    });
});
