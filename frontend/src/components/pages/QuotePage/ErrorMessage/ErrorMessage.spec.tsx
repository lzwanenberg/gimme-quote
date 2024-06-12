import React from 'react';
import { render, screen } from '@testing-library/react';
import ErrorMessage from './ErrorMessage';

describe('ErrorMessage', () => {
    test('renders error message', () => {
        render(<ErrorMessage error={"Something went wrong."} />);

        const contentText = screen.getByText('Error: Something went wrong.');
        expect(contentText).toBeInTheDocument();
    });
});
