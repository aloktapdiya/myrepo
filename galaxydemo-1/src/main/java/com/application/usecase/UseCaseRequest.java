package com.application.usecase;

public interface UseCaseRequest<Request>
{
  void execute(Request command);
}
