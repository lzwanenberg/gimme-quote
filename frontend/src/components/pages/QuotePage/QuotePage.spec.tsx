import React from 'react';
import { render, waitFor, act } from '@testing-library/react';
import QuotePage from './QuotePage';
import QuoteService from '@/services/QuoteService/QuoteService';
import { instance, mock, when } from 'ts-mockito';
import { useQuoteService } from '@/hooks/useQuoteService';

const quoteServiceMock = mock(QuoteService);

when(quoteServiceMock.fetchQuote()).thenResolve({
    success: true,
    response: {
        author: "John Doe",
        content: "Hello, world!"
    }
});

jest.mock('@/hooks/useQuoteService');
const mockUseQuoteService = jest.mocked(useQuoteService);

describe('QuotePage', () => {
    describe("when quote service successfully fetches quote", () => {
        it('renders quote', async () => {
            mockUseQuoteService.mockReturnValue(instance(quoteServiceMock))

            let component: ReturnType<typeof render>;

            act(() => {
                component = render(<QuotePage />);
            });

            await waitFor(() => {
                const quoteContent = component.getByText('Hello, world!');
                expect(quoteContent).toBeInTheDocument();
            });
        });
    });
});
