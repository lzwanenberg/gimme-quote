import React from 'react';
import { render, waitFor, act } from '@testing-library/react';
import QuotePage from './QuotePage';
import QuoteService from '@/services/QuoteService/QuoteService';
import { instance, mock, when } from 'ts-mockito';
import { useQuoteService } from '@/hooks/useQuoteService';
import { createFetchQuoteFailure, createFetchQuoteSuccess, createQuoteResponse } from '@/test-utils/factories';
import FetchQuoteResult from '@/services/QuoteService/types/FetchQuoteResult';

jest.mock('@/hooks/useQuoteService');
const mockUseQuoteService = jest.mocked(useQuoteService);

const mockQuoteService = ({ returnValue }: { returnValue: Promise<FetchQuoteResult> }) => {
    const quoteServiceMock = mock(QuoteService);
    when(quoteServiceMock.fetchQuote()).thenReturn(returnValue)
    mockUseQuoteService.mockReturnValue(instance(quoteServiceMock));
};

describe('QuotePage', () => {
    describe("when quote service has not yet returned a result", () => {
        it("renders loading indicator", async () => {
            const pendingPromise = new Promise<FetchQuoteResult>(() => { });
            mockQuoteService({ returnValue: pendingPromise });

            const result = render(<QuotePage />);

            await waitFor(() => {
                const loading = result.getByText('Loading...');
                expect(loading).toBeInTheDocument();
            });
        });
    });

    describe("when quote service successfully fetches quote", () => {
        it('renders quote and author', async () => {
            mockQuoteService({
                returnValue: Promise.resolve(
                    createFetchQuoteSuccess({
                        response: createQuoteResponse({
                            author: "Gandalf",
                            content: "All we have to decide is what to do with the time that is given us."
                        })
                    })
                )
            });

            const result = render(<QuotePage />);

            await waitFor(() => {
                const author = result.getByText('Gandalf');
                expect(author).toBeInTheDocument();

                const content = result.getByText('All we have to decide is what to do with the time that is given us.');
                expect(content).toBeInTheDocument();
            });
        });
    });

    describe("when quote service returns error while fetching quote", () => {
        it('renders error', async () => {
            mockQuoteService({
                returnValue: Promise.resolve(
                    createFetchQuoteFailure({
                        error: "Something went wrong"
                    })
                )
            });

            const result = render(<QuotePage />);

            await waitFor(() => {
                const error = result.getByText('Error: Something went wrong');
                expect(error).toBeInTheDocument();
            });
        });
    });

    describe("when quote service crashes while fetching quote", () => {
        it('renders error', async () => {
            mockQuoteService({
                returnValue: Promise.reject("Something went wrong")
            });

            const result = render(<QuotePage />);

            await waitFor(() => {
                const error = result.getByText('Error: Something went wrong');
                expect(error).toBeInTheDocument();
            });
        });
    });
});
