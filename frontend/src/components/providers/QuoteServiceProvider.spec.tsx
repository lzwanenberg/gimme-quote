import React, { useContext } from 'react';
import { render } from '@testing-library/react';
import { QuoteServiceProvider, QuoteServiceContext } from './QuoteServiceProvider';
import { createQuoteServiceConfig } from '@/test-utils/factories';
import QuoteService from '@/services/QuoteService/types/QuoteService';
import createQuoteService from '@/services/QuoteService/createQuoteService';


jest.mock('@/services/QuoteService/createQuoteService');
const mockCreateQuoteService = jest.mocked(createQuoteService);

const TestComponent: React.FC = () => {
    const context = useContext(QuoteServiceContext) as any as string;

    return <div>{context}</div>;
};

describe('QuoteServiceProvider', () => {
    it('instantiates QuoteService with provided config', () => {
        const quoteServiceMock = "MyQuoteService" as any as QuoteService;
        const config = createQuoteServiceConfig({
            host: '127.0.0.1',
            port: 3456
        });

        const mockCreator = jest.fn().mockReturnValue(quoteServiceMock);
        mockCreateQuoteService.mockImplementation(mockCreator);

        render(
            <QuoteServiceProvider config={config}>
                <TestComponent />
            </QuoteServiceProvider>
        );

        expect(mockCreator).toHaveBeenCalledTimes(1);
        expect(mockCreator.mock.calls[0][0].host).toEqual("127.0.0.1");
        expect(mockCreator.mock.calls[0][0].port).toEqual(3456);
    });

    it('makes QuoteService available through context API', () => {
        const quoteServiceMock = "MyQuoteService" as any as QuoteService;
        const config = createQuoteServiceConfig();

        const mockCreator = jest.fn().mockReturnValue(quoteServiceMock);
        mockCreateQuoteService.mockImplementation(mockCreator);

        const { getByText } = render(
            <QuoteServiceProvider config={config}>
                <TestComponent />
            </QuoteServiceProvider>
        );

        expect(getByText("MyQuoteService")).toBeInTheDocument();
    });
});
