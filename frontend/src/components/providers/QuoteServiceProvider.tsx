import createQuoteService from '@/services/QuoteService/createQuoteService';
import QuoteService from '@/services/QuoteService/types/QuoteService';
import QuoteServiceConfig from '@/services/QuoteService/types/QuoteServiceConfig';
import React, { createContext } from 'react';

export const QuoteServiceContext = createContext<QuoteService | null>(null);

type Props = {
    config: QuoteServiceConfig;
    children?: React.ReactNode;
};

export const QuoteServiceProvider: React.FC<Props> = ({ config, children }) => {
    const quoteService = createQuoteService(config);

    return (
        <QuoteServiceContext.Provider value={quoteService}>
            {children}
        </QuoteServiceContext.Provider>
    );
};