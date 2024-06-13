
// createQuoteService.test.ts
import createQuoteService from './createQuoteService';
import { createQuoteResponse, createQuoteServiceConfig } from '@/test-utils/factories';

global.fetch = jest.fn();
const fetch = global.fetch as jest.Mock;

describe("createQuoteService", () => {
    beforeEach(() => {
        fetch.mockClear();
    });

    describe("#fetchQuote", () => {
        it("calls fetch with correct URL", async () => {
            fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => createQuoteResponse(),
            });

            const config = createQuoteServiceConfig({
                host: '127.0.0.1',
                port: 8123
            });

            const quoteService = createQuoteService(config);
            await quoteService.fetchQuote();

            expect(fetch).toHaveBeenCalledTimes(1);
            expect(fetch).toHaveBeenCalledWith(`http://127.0.0.1:8123`);
        });

        describe("when fetch result is ok", () => {
            it("returns response", async () => {
                const response = createQuoteResponse();

                fetch.mockResolvedValueOnce({
                    ok: true,
                    json: async () => response,
                });

                const config = createQuoteServiceConfig();

                const quoteService = createQuoteService(config);
                const result = await quoteService.fetchQuote();

                expect(result).toEqual({
                    success: true,
                    response
                });
            });
        });

        describe("when fetch result is not ok", () => {
            it("returns error", async () => {
                const response = createQuoteResponse();

                fetch.mockResolvedValueOnce({
                    ok: false,
                    json: async () => response,
                    status: 500,
                    statusText: "Internal Server Error"
                });

                const config = createQuoteServiceConfig();

                const quoteService = createQuoteService(config);
                const result = await quoteService.fetchQuote();

                expect(result).toEqual({
                    success: false,
                    error: "Failed to fetch quote: 500 Internal Server Error"
                });
            });
        });

        describe("when fetch throws an error", () => {
            it("returns error", async () => {
                fetch.mockRejectedValueOnce(new Error('Network error'));

                const config = createQuoteServiceConfig();

                const quoteService = createQuoteService(config);
                const result = await quoteService.fetchQuote();

                expect(result).toEqual({ success: false, error: 'Network error' });
            })
        });
    });
});
