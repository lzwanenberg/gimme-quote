import { renderHook } from '@testing-library/react'
import { QuoteServiceContext } from '../components/providers/QuoteServiceProvider';
import { useQuoteService } from './useQuoteService';
import { mock } from 'ts-mockito';
import QuoteService from '@/services/QuoteService/types/QuoteService';

describe("useQuoteService", () => {
    describe("when used outside QuoteServiceProvider", () => {
        it("throws error", () => {
            expect.hasAssertions();

            renderHook(() => {
                try {
                    useQuoteService();
                } catch (error) {
                    expect(error).toBeInstanceOf(Error);
                    if (!(error instanceof Error)) return;
                    expect(error.message).toEqual(
                        "useQuoteService must be used within a QuoteServiceProvider"
                    );
                }
            })
        });
    });

    describe("when used within QuoteServiceProvider", () => {
        it("returns QuoteService instance", () => {
            const mockQuoteService = mock<QuoteService>();
            const wrapper: React.FC<{ children?: React.ReactNode }> = ({ children }) => (
                <QuoteServiceContext.Provider value={mockQuoteService}>
                    {children}
                </QuoteServiceContext.Provider>
            );

            const { result } = renderHook(() => useQuoteService(), { wrapper });

            expect(result.current).toBe(mockQuoteService);
        });
    })
});
