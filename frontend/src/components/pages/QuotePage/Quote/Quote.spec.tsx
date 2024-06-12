import React from 'react';
import { render, screen } from '@testing-library/react';
import Quote from './Quote';
import { createQuoteResponse } from '@/test-utils';

describe('Quote', () => {
    test('renders quote content', () => {
        const response = createQuoteResponse({
            content: 'May the Force be with you.'
        });

        render(<Quote response={response} />);

        const contentText = screen.getByText('May the Force be with you.');
        expect(contentText).toBeInTheDocument();
    });

    test('renders quote author', () => {
        const response = createQuoteResponse({
            author: 'Yoda'
        });

        render(<Quote response={response} />);

        const contentText = screen.getByText('Yoda');
        expect(contentText).toBeInTheDocument();
    });
});